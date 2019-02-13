import org.springframework.cloud.contract.spec.Contract

REQUEST_URL = '/test'

JWT_HAPPY = 'ey'
BEZEICHNUNG = 'bezeichnungX'
VERSION_NR = '1'

[
        Contract.make {
            name("test")
            description("test")
            request {
                method 'POST'
                url "${REQUEST_URL}"
                body(
                        bezeichnung: "${BEZEICHNUNG}"
                )
                headers {
                    contentType(applicationJson())
                    header(authorization(), JWT_HAPPY)
                }
                bodyMatchers {
                    jsonPath('$.bezeichnung', byRegex(nonEmpty()))
                }
            }
            response {
                status 200
                body(
                        bezeichnung: "${BEZEICHNUNG}",
                        versionNr: "${VERSION_NR}"
                )
                bodyMatchers {
                    jsonPath('$._links.self.href', byRegex('^.*/api-test/v0/test/1/test/\\d+$'))
                }
                headers { contentType(applicationJson()) }
            }
        }
]
