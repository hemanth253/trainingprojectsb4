node{
  stage('SCM Checkout'){
    echo 'Hi There'
    git url 'https://github.com/hemanth253/trainingprojectsb4'
  }
  stage('Compile-Package'){
    sh 'mvn package'
  }
}
