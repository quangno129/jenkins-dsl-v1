def call(String branchName = 'main') {
    node {
        stage("Checkout") {
            deleteDir()
            sh """
              git clone https://github.com/quangno129/jenkins-dsl-v1 lib --depth 1
            """
        }

        stage("Create folders and jobs") {
            jobDsl targets: [
                    'lib/jobs/core/*.groovy',
            ].join('\n')
        }
    }
}
