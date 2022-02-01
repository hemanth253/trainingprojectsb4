node{
  stage('SCM Checkout'){
    echo 'Hi There'
    git 'https://github.com/hemanth253/trainingprojectsb4'
//     git branch: 'master',
//         url: 'https://github.com/hemanth253/trainingprojectsb4'
  }
  stage('Compile-Package'){
    sh 'mvn package'
  }
  stage('Email Notification'){
    mail bcc: '180010023@iitdh.ac.in', body: 'Hey there!!', cc: 'nhemanthreddy7@gmail.com', from: '', replyTo: '', subject: 'Test', to: 'nhemanthreddy0@gmail.com'
  }
}
