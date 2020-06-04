// 这是一个jenkins pipline 文件
node {

// 定义环境变量 TAG 为docker私库标签 ；TAG1 是docker 阿里云仓库标签(docker login 地址为registry.cn-hangzhou.aliyuncs.com/sourcegarden/) ;TAG2 是公司微服务UAT环境下的harbor;TAG3 是公司微服务dev环境下的harbor
withEnv(['TAG=registry.docker.me/sourcegarden/$JOB_NAME:$BUILD_NUMBER','TAG1=registry.cn-hangzhou.aliyuncs.com/sourcegarden/$JOB_NAME:$BUILD_NUMBER','TAG2=10.150.33.104/microservice/$JOB_NAME:$BUILD_NUMBER','TAG3=10.167.202.127/microservice-uat/$JOB_NAME:$BUILD_NUMBER']) {

//下面的工具均在jenkins 设置菜单的“Global Tool Configuration”选项下配置，其中rancher-compose为自定义工具
//在”Global Tool Configuration“中自定义工具需要安装插件”extra tool installers plugin"
//定义maven工具路径到变量
def mvnHome = tool 'maven350'
//查看工具是否存在 并且查看当前环境变量
sh "ls -l ${mvnHome}  && echo $Git_Rancher && echo $Git_Project && env "

//定义rancher-compose工具路径到变量
def RancherHome = tool 'rancher-compose'
//def RancherHome = "/var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/rancher-compose"
//查看工具是否存在
sh "ls -lh  ${RancherHome}"


// 设置时间戳
timestamps {

//拉取项目代码(Git_Rancher Git_Project 变量需要在Jenkins的参数化构建选项里填写)
stage 'Project Code Checkout'
checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '0a800d45-9fb7-4d47-9aee-c8cdda5470f4', url: '${Git_Project}']]])

//拉取容器构建相关代码
stage 'Rancher Code Checkout'
dir('prebuild-docker/') {
checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '0a800d45-9fb7-4d47-9aee-c8cdda5470f4', url: '${Git_Rancher}']]])
}

//Maven 构建
stage 'Maven Build'

sh "pwd && ls -l  && ${mvnHome}/bin/mvn -version && ${mvnHome}/bin/mvn clean install  -P uat -Dmaven.test.skip=true "

//Docker镜像打包,并且运行docker_template.sh脚本用来生成rancher-compose、docker-compose、dockerfile文件
stage 'Image Build'
sh 'ls -l &&echo $JOB_NAME && cp target/${JOB_NAME}-0.0.1-SNAPSHOT.jar prebuild-docker/ && chmod +x prebuild-docker/docker_template.sh && sed -i s/Jenkins_Rancher_Job_Name/"$JOB_NAME"/g prebuild-docker/docker_template.sh && prebuild-docker/docker_template.sh '

dir('prebuild-docker/') {

sh " pwd && ls -l && docker build --build-arg Git_Project=${Git_Project} -t $TAG3 -f Dockerfile_jre8_tomcat8 . "
}

//推送Docker镜像到仓库
stage 'Push Image'
dir(' prebuild-docker/') {
sh "docker push $TAG3 "
}

//开始编排容器========Rancher UAT 环境
stage 'Deploy UAT'
//设置工作路径 (注意这里是相对“WORKSPACE”的相对路径)
dir('prebuild-docker/'){

//替换地址   使其镜像地址为阿里云的镜像仓库地址
//key为rancher环境key;
sh "sed -i s@Jenkins_Rancher_Job_Image@$TAG3@g docker-compose.yml"

sh "${RancherHome}/rancher-compose  --debug --url http://10.167.202.165:8080 --access-key F5CCC5C1D238E0787AE2   --secret-key Sn4DB1xzUpKLQSqhSYNJdXct1VFzS473jqUMLEd3   -p ${Rancher_Stack} up -d -c"
sh "${RancherHome}/rancher-compose  --debug --url http://10.167.202.165:8080 --access-key F5CCC5C1D238E0787AE2   --secret-key Sn4DB1xzUpKLQSqhSYNJdXct1VFzS473jqUMLEd3   -p ${Rancher_Stack} -d -c"

}

//开始编排容器============Rancher Test 环境
stage 'Deploy Test'
//设置超时时间，时间到如果未操作则后面的动作将取消
timeout(time: 3, unit: 'SECONDS') {
input '是否部署到测试环境?'
}
dir('prebuild-docker/'){
sh "sed -i s/192.168.130.2/registry.aliyuncs.com/g docker-compose.yml "
//key为rancher 中 rancher node for Test 环境中的key;
tool name: 'rancher-compose', type: 'com.cloudbees.jenkins.plugins.customtools.CustomTool'
sh "${RancherHome}/rancher-compose  --url http://10.167.202.165:8080 --access-key CA075164770E50350829 --secret-key myPae4EBdnEDXWPjAYK96aWe1hocn4wpcBBvjs9A  -p microservice up -p -d -u "
sh "${RancherHome}/rancher-compose  --url http://10.167.202.165:8080 --access-key CA075164770E50350829 --secret-key myPae4EBdnEDXWPjAYK96aWe1hocn4wpcBBvjs9A  -p microservice up -d -c "
}

}
}
}
