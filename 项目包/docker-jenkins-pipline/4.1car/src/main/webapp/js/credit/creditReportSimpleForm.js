$(function(){
	
	//金额验证
	function addKeyup(){
		$('input[type=text][money=1]').each(function(index, element) {
			$(element).keyup(function(){
				//只输入数字和两位小数
				this.value = this.value.replace(/[^\d.]/g,""); //清除"数字"和"."以外的字符
				this.value = this.value.replace(/^\./g,""); //验证第一个字符是数字而不是
				this.value = this.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的
				this.value = this.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
				this.value = this.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'); //只能输入两个小数
			});
		});
	}
	
	//金额验证
	addKeyup();
	
	// 返回按钮
	$("#backBtn").click(function(){
		window.location = ctx + "/verify/check/fetchTaskItems";
	});	
	
});

//格式化金额
function formatMoney(flag, index, name){
	//显示字段
	var inputName = $("#" + flag + "InfoForm").find("input[name='" + name + "Name']");
	//隐藏字段
	var input = $("#" + flag + "InfoForm").find("input[name='" + name + "']");
	//显示值
	var wd = $(inputName[index]).val();
	var s = Number(wd.replace(/,/g,''));
	//设置隐藏值
	$(input[index]).val(s)
	if(s != null && s != "" && s != 0){
		s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(2) + ""; 
		var l = s.split(".")[0].split("").reverse(),r = s.split(".")[1]; 
		var t = ""; 
		for (var i = 0; i < l.length; i++) { 
			t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : ""); 
		}
		if(r != "00"){
			$(inputName[index]).val(t.split("").reverse().join("") + "." + r);
		}else{
			$(inputName[index]).val(t.split("").reverse().join(""));
		}
	}
	
}


//添加信息
function addTr(flag){
	
	// 添加信息tr
	var trInfo = $("#" + flag + "TableHide").find("tbody");
	var htmInfo = trInfo.cloneSelect2(true,true);
	$("#" + flag + "Table").append(htmInfo);
	// 调整序号
	setNum(flag);
}

//调整序号
function setNum(flag) {
	
	var allInfoNum = $("#" + flag + "Table").find("tbody").find("input[name='num']");
	allInfoNum.each(function(i,item){
		$(this).val(i+1);
		$(this).parent("td").parent("tr").attr("id","infoTr" + (i + 1));
		$(this).parent("td").parent("tr").find("td a[name='aRemoveInfo']").attr("onClick","removeTr('" + flag + "', '" + (i + 1)+"')");
		//信用卡信息增加金额转换
		if (flag == "card") {
			$(this).parent("td").parent("tr").find("td input[name='limitName']").attr("onblur","formatMoney('card', '" + i + "', 'limit')");
			$(this).parent("td").parent("tr").find("td input[name='usedLimitName']").attr("onblur","formatMoney('card', '" + i + "', 'usedLimit')");
			$(this).parent("td").parent("tr").find("td input[name='overdueAmountName']").attr("onblur","formatMoney('card', '" + i + "', 'overdueAmount')");
		}
		//贷款明细信息增加金额转换
		if (flag == "loan") {
			$(this).parent("td").parent("tr").find("td input[name='conteactAmountName']").attr("onblur","formatMoney('loan', '" + i + "', 'conteactAmount')");
			$(this).parent("td").parent("tr").find("td input[name='loanBalanceName']").attr("onblur","formatMoney('loan', '" + i + "', 'loanBalance')");
			$(this).parent("td").parent("tr").find("td input[name='overdueAmountName']").attr("onblur","formatMoney('loan', '" + i + "', 'overdueAmount')");
		}
		
	});
}

// 删除信息
function removeTr(flag, index){
	// 数据库删除
	var id = $("#" + flag + "InfoForm").find("input[name='id']"); // 主键
	var i = index - 1;
	var idVal = $(id[i]).val();
	// 后台数据库删除
	if (idVal != "") {
		
		if (confirm("确认要删除吗？")) {
			var url = "";
			// 删除信用卡url
			if (flag == "card") {
				url = ctx + "/credit/creditReportSimple/deleteCardInfoById";
			} else if (flag == "loan") {
				// 删除贷款url
				url = ctx + "/credit/creditReportSimple/deleteLoanInfoById";
			} else if (flag == "query") {
				// 删除查询信息卡url
				url = ctx + "/credit/creditReportSimple/deleteQueryInfoById";
			}
			
			$.ajax({
				url:url,
				data : {
					id : idVal
				},
				type: "post",
				dataType:'json',
				success:function(data){
					if(data) {
						art.dialog.tips('删除成功!');
					} else {
						art.dialog.tips('删除失败!');
					}
				}
			});
	
		} else {
			return;
		}
	}
	
	// 移除该行
	$("#" + flag + "Table").find("#infoTr" + index).remove();
	// 调整序号
	setNum(flag);
}


// 拼接信用卡信息json数据
function makeParamCardInfo() {
	// 信用卡信息
	var id = $("#cardInfoForm").find("input[name='id']"); // 主键
	var relationId = $("#cardInfoForm").find("input[name='relationId']"); // 外键
	
	var accountStatus = $("#cardInfoForm").find("select[name='accountStatus']"); // 账户状态
	var currency = $("#cardInfoForm").find("select[name='currency']"); // 币种
	var isOverdue = $("#cardInfoForm").find("select[name='isOverdue']"); // 是否发生过逾期
	var issueDay = $("#cardInfoForm").find("input[name='issueDay']"); // 发放日期
	var abortDay = $("#cardInfoForm").find("input[name='abortDay']"); // 截至年月
	var limit = $("#cardInfoForm").find("input[name='limit']"); // 额度
	var usedLimit = $("#cardInfoForm").find("input[name='usedLimit']"); // 已使用额度
	var overdueAmount = $("#cardInfoForm").find("input[name='overdueAmount']"); // 逾期金额
	var overdueNo = $("#cardInfoForm").find("input[name='overdueNo']"); // 最近5年逾期次数
	var overdueForNo = $("#cardInfoForm").find("input[name='overdueForNo']"); // 最近五年90天以上逾期次数
	var cancellationDay = $("#cardInfoForm").find("input[name='cancellationDay']"); // 销户年月
	
	var param = "";
	for(var i = 0; i < id.length; i++ ){
		param += "&creditCardInfoList[" + i + "].id=" + $(id[i]).val();
		param += "&creditCardInfoList[" + i + "].relationId=" + $(relationId[i]).val();
		
		param += "&creditCardInfoList[" + i + "].accountStatus=" + $(accountStatus[i]).val();
		param += "&creditCardInfoList[" + i + "].currency=" + $(currency[i]).val();
		param += "&creditCardInfoList[" + i + "].isOverdue=" + $(isOverdue[i]).val();
		param += "&creditCardInfoList[" + i + "].issueDay=" + $(issueDay[i]).val();
		param += "&creditCardInfoList[" + i + "].abortDay=" + $(abortDay[i]).val();
		param += "&creditCardInfoList[" + i + "].limit=" + $(limit[i]).val();
		param += "&creditCardInfoList[" + i + "].usedLimit=" + $(usedLimit[i]).val();
		param += "&creditCardInfoList[" + i + "].overdueAmount=" + $(overdueAmount[i]).val();
		param += "&creditCardInfoList[" + i + "].overdueNo=" + $(overdueNo[i]).val();
		param += "&creditCardInfoList[" + i + "].overdueForNo=" + $(overdueForNo[i]).val();
		param += "&creditCardInfoList[" + i + "].cancellationDay=" + $(cancellationDay[i]).val();
		
	}
	// 关联id
	param += "&id=" + $("#creditReportSimpleId").val();
	
	return param;
}

//拼接信用卡信息json数据
function makeParamLoanInfo() {
	// 信用卡信息
	var id = $("#loanInfoForm").find("input[name='id']"); // 主键
	var relationId = $("#loanInfoForm").find("input[name='relationId']"); // 外键
	
	var accountStatus = $("#loanInfoForm").find("select[name='accountStatus']"); // 账户状态
	var currency = $("#loanInfoForm").find("select[name='currency']"); // 贷款种类
	var isOverdue = $("#loanInfoForm").find("select[name='isOverdue']"); // 是否发生过逾期
	var issueDay = $("#loanInfoForm").find("input[name='issueDay']"); // 发放日期
	var abortDay = $("#loanInfoForm").find("input[name='abortDay']"); // 到期日期
	var actualDay = $("#loanInfoForm").find("input[name='actualDay']"); // 截至年月
	var conteactAmount = $("#loanInfoForm").find("input[name='conteactAmount']"); // 贷款合同金额
	var loanBalance = $("#loanInfoForm").find("input[name='loanBalance']"); // 贷款余额
	var overdueAmount = $("#loanInfoForm").find("input[name='overdueAmount']"); // 逾期金额
	var overdueNo = $("#loanInfoForm").find("input[name='overdueNo']"); // 最近5年逾期次数
	var overdueForNo = $("#loanInfoForm").find("input[name='overdueForNo']"); // 最近五年90天以上逾期次数
	var settleDay = $("#loanInfoForm").find("input[name='settleDay']"); // 结清年月
	
	var param = "";
	for(var i = 0; i < id.length; i++ ){
		param += "&creditLoanInfoList[" + i + "].id=" + $(id[i]).val();
		param += "&creditLoanInfoList[" + i + "].relationId=" + $(relationId[i]).val();
		
		param += "&creditLoanInfoList[" + i + "].accountStatus=" + $(accountStatus[i]).val();
		param += "&creditLoanInfoList[" + i + "].currency=" + $(currency[i]).val();
		param += "&creditLoanInfoList[" + i + "].isOverdue=" + $(isOverdue[i]).val();
		param += "&creditLoanInfoList[" + i + "].issueDay=" + $(issueDay[i]).val();
		param += "&creditLoanInfoList[" + i + "].abortDay=" + $(abortDay[i]).val();
		param += "&creditLoanInfoList[" + i + "].actualDay=" + $(actualDay[i]).val();
		param += "&creditLoanInfoList[" + i + "].conteactAmount=" + $(conteactAmount[i]).val();
		param += "&creditLoanInfoList[" + i + "].loanBalance=" + $(loanBalance[i]).val();
		param += "&creditLoanInfoList[" + i + "].overdueAmount=" + $(overdueAmount[i]).val();
		param += "&creditLoanInfoList[" + i + "].overdueNo=" + $(overdueNo[i]).val();
		param += "&creditLoanInfoList[" + i + "].overdueForNo=" + $(overdueForNo[i]).val();
		param += "&creditLoanInfoList[" + i + "].settleDay=" + $(settleDay[i]).val();
	}
	// 关联id
	param += "&id=" + $("#creditReportSimpleId").val();
	
	return param;
}

//拼接信用卡信息json数据
function makeParamQueryInfo() {
	// 信用卡信息
	var id = $("#queryInfoForm").find("input[name='id']"); // 主键
	var relationId = $("#queryInfoForm").find("input[name='relationId']"); // 外键
	
	var queryDay = $("#queryInfoForm").find("input[name='queryDay']"); // 查询日期
	var queryType = $("#queryInfoForm").find("select[name='queryType']"); // 查询原因
	
	var param = "";
	for(var i = 0; i < id.length; i++ ){
		param += "&creditQueryRecordList[" + i + "].id=" + $(id[i]).val();
		param += "&creditQueryRecordList[" + i + "].relationId=" + $(relationId[i]).val();
		
		param += "&creditQueryRecordList[" + i + "].queryDay=" + $(queryDay[i]).val();
		param += "&creditQueryRecordList[" + i + "].queryType=" + $(queryType[i]).val();
	}
	// 关联id
	param += "&id=" + $("#creditReportSimpleId").val();
	
	return param;
}

//拼接保证人代偿信息json数据
function makeParamPaybackInfo() {
	// 保证人代偿信息
	var id = $("#paybackInfoForm").find("input[name='id']"); // 主键
	var relationId = $("#paybackInfoForm").find("input[name='relationId']"); // 外键
	
	var recentlyPaybackTime = $("#paybackInfoForm").find("input[name='recentlyPaybackTime']"); // 发放日期
	var paybackOrg = $("#paybackInfoForm").find("input[name='paybackOrg']"); // 额度
	var totalPaybackAmount = $("#paybackInfoForm").find("input[name='totalPaybackAmount']"); // 已使用额度
	var lastPaybackDate = $("#paybackInfoForm").find("input[name='lastPaybackDate']"); // 逾期金额
	var residualAmount = $("#paybackInfoForm").find("input[name='residualAmount']"); // 最近5年逾期次数
	
	var param = "";
	for(var i = 0; i < id.length; i++ ){
		param += "&creditPaybackInfoList[" + i + "].id=" + $(id[i]).val();
		param += "&creditPaybackInfoList[" + i + "].relationId=" + $(relationId[i]).val();
		
		param += "&creditPaybackInfoList[" + i + "].recentlyPaybackTime=" + $(recentlyPaybackTime[i]).val();
		param += "&creditPaybackInfoList[" + i + "].paybackOrg=" + $(paybackOrg[i]).val();
		param += "&creditPaybackInfoList[" + i + "].totalPaybackAmount=" + $(totalPaybackAmount[i]).val();
		param += "&creditPaybackInfoList[" + i + "].lastPaybackDate=" + $(lastPaybackDate[i]).val();
		param += "&creditPaybackInfoList[" + i + "].residualAmount=" + $(residualAmount[i]).val();
		
	}
	// 关联id
	param += "&id=" + $("#creditReportSimpleId").val();
	
	return param;
}


// 保存信息
function saveTr(flag){
	//alert(flag);
	
	//验证表单
	if (!$("#creditReportSimpleForm").validate().form()) {
		return;
	}
	
	//验证表单
	if (!checkForm($("#" + flag + "InfoForm"))) {
		return;
	}
	
	var data = "";
	var url = "";
	// 拼接信用卡json
	if (flag == "card") {
		data = makeParamCardInfo();
		url = ctx + "/credit/creditReportSimple/saveCardInfo";

	} else if (flag == "loan") {
		// 拼接贷款json
		data = makeParamLoanInfo();
		url = ctx + "/credit/creditReportSimple/saveLoanInfo";

	} else if (flag == "query") {
		// 拼接查询信息卡json
		data = makeParamQueryInfo();
		url = ctx + "/credit/creditReportSimple/saveQueryInfo";

	} else if (flag == "payback") {
		// 拼接保证人代偿信息json
		data = encodeURI(makeParamPaybackInfo());
		url = ctx + "/credit/creditReportSimple/savePaybackInfo";
	}
	
	// 简版贷记卡负债信息
	$.ajax({
		url: url,
		data: data,
		type: "post",
		dataType: 'json',
		success:function(data){
			if(data) {
				art.dialog.tips('保存成功!');
				// 刷新信用卡div
				if (flag == "card") {
					$("#cardInfoTab").load(ctx+"/credit/creditReportSimple/initCardInfo",{"id":$("#creditReportSimpleId").val()},function(){});
				} else if (flag == "loan") {
					// 刷新贷款div
					$("#loanInfoTab").load(ctx+"/credit/creditReportSimple/initLoanInfo",{"id":$("#creditReportSimpleId").val()},function(){});
				} else if (flag == "query") {
					// 刷新查询信息卡div
					$("#queryInfoTab").load(ctx+"/credit/creditReportSimple/initQueryInfo",{"id":$("#creditReportSimpleId").val()},function(){});
				}
				
			} else {
				art.dialog.tips('保存失败!');
			}
		}
	});
}

//保存查询日期
function saveQueryTime() {

	//验证表单
	if (!$("#creditReportSimpleForm").validate().form()) {
		return;
	}
	$.ajax({
		url : ctx + "/credit/creditReportSimple/saveQueryTime",
		async : false,
		cache : false,
		type : "POST",
		data : {
			id : $("#creditReportSimpleId").val(),
			queryTime : $("#queryTime").val()
		},
		dataType:'json',
		success:function(data){
			if(data) {
				art.dialog.tips('更新成功!');
			} else {
				art.dialog.tips('更新失败!');
			}
		}
	});
	
} 

//跳转到网络征信登录界面
function initEnterpriseCreditWebLoad(){
	window.location.href = ctx + "/credit/creditReportSimple/initWeb?id=" + $("#creditReportSimpleId").val();
}


//跳转到静态网页
function initHtmlUrl(){
	OpenWindow = window.open("initHtmlUrl?id=" + $("#creditReportSimpleId").val(), "height=400,toolbar=no, width=600,top=200, left=300, scrollbars=yes, menubar=no, status=no, titlebar=no, toolbar=no, location=no");

}



