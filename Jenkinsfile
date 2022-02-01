node{
  stage('SCM Checkout'){
    git 'https://github.com/hemanth253/trainingprojectsb4'
  }
  stage('Compile-Package'){
    sh 'mvn package'
  }
}
