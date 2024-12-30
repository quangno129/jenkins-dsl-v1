def call(String branchName = 'main') {
    node {
        stage("Checkout") {
            withCredentials([string(credentialsId: 'github-token', variable: 'TOKEN')]) {
                deleteDir()
                sh """
                    printenv
                    git clone -b main https://github.com/quangno129/jenkins-dsl-v1 lib --depth 1
                """
            }
        }

        stage("Create folders and jobs") {
            jobDsl targets: [
                    'lib/jobs/core/*.groovy',
            ].join('\n')
        }
    }
}
