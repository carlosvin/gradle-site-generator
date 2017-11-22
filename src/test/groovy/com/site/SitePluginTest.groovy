package com.site

import org.gradle.testkit.runner.GradleRunner
import static org.gradle.testkit.runner.TaskOutcome.*
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification
import java.nio.file.Paths

class SitePluginTest extends Specification {
    @Rule final TemporaryFolder testProjectDir = new TemporaryFolder()
    File buildFile

    def setup() {
        buildFile = testProjectDir.newFile('build.gradle')
    }

    def "build"() {
        given:
		def siteName = 'ASDF'
        buildFile << """
            plugins {
                id 'static-site-generator'
            }
            configuration {
                siteName = '$siteName'
            }
        """

        when:
        def result = GradleRunner.create()
                .withProjectDir(testProjectDir.root)
                .withPluginClasspath()
                .withArguments('build')
                .build()

        then:
        result.output.contains(siteName)
        result.task(":build").outcome == SUCCESS
        //new File('ASDF').exists()
    }
}