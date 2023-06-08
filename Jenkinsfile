pipeline {
  agent any
  stages {
    stage('Build Parameters') {
      when {
        branch "main"
      }
      steps {
        script {
          properties([parameters([booleanParam(defaultValue: false,
                  description: 'Deploy',
                  name: 'DEPLOY')])])
        }
      }
    }

    stage('Build Project') {
      when {
        expression {
          return params.DEPLOY != true
        }
      }
      steps {
        sh 'bin/mvn clean install --no-transfer-progress --update-snapshots'
      }
    }

    stage('Build and Publish') {
      when {
        branch 'main'
        expression {
          return params.DEPLOY == true
        }
      }
      steps {
        sh 'bin/mvn clean deploy --update-snapshots --no-transfer-progress'
      }
    }

    stage('Release') {
      when {
        branch "main"
        expression {
          return params.RELEASE == true
        }
      }
      steps {
        sh "bin/dev release '$VERSION'"
      }
    }
  }
  post {
    always {
      junit allowEmptyResults: true,
              testResults: 'test-results.xml'
      archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
    }
  }
}
