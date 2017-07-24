def loggingDir = (System.getProperty('catalina.base') ? System.getProperty('catalina.base') + '/logs' : './logs')
def appName = "ala-bootstrap2"
// log4j configuration
log4j = {
// Example of changing the log pattern for the default console
// appender:
    appenders {
        environments {
            production {
                String logFilename = "${loggingDir}/log-${appName}.log"
                println("Application will log to file: ${logFilename}")
                rollingFile name: "tomcatLog", maxFileSize: '1MB', file: "${logFilename}", threshold: org.apache.log4j.Level.WARN, layout: pattern(conversionPattern: "%d %-5p [%c{1}] %m%n")
            }
            development {
                console name: "stdout", layout: pattern(conversionPattern: "%d %-5p [%c{1}] %m%n"), threshold: org.apache.log4j.Level.DEBUG
            }
            test {
                String logFilename = "/tmp/${appName}"
                println("Application will log to file: ${logFilename}")
                rollingFile name: "tomcatLog", maxFileSize: '1MB', file: "${logFilename}", threshold: org.apache.log4j.Level.DEBUG, layout: pattern(conversionPattern: "%d %-5p [%c{1}] %m%n")
            }
        }
    }
    root {
        // change the root logger to my tomcatLog file
        error 'tomcatLog'
        warn 'tomcatLog'
        additivity = true
    }

    error   'grails.spring.BeanBuilder',
            'grails.plugin.webxml',
            'grails.plugin.cache.web.filter',
            'grails.app.services.org.grails.plugin.resource',
            'grails.app.taglib.org.grails.plugin.resource',
            'grails.app.resourceMappers.org.grails.plugin.resource'

    warn   'au.org.ala.cas.client'

    debug   'grails.app','au.org.ala.cas','au.org.ala.userdetails'
}