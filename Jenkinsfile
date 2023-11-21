node {
  stage("Clone the project") {
    git branch: 'main', url: 'https://github.com/ArslanKucukkafa/Field-Managment-Services.git'
  }

  stage("Compilation") {
    sh "./mvnw clean install -DskipTests"
  }
}