package com.site

import org.gradle.testkit.runner.GradleRunner
import static org.gradle.testkit.runner.TaskOutcome.*
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

class SitePluginTest extends Specification {
    @Rule final TemporaryFolder testProjectDir = new TemporaryFolder()
    File buildFile

    def setup() {
        buildFile = testProjectDir.newFile('build.gradle')
    }

    def "build"() {
        given:
        buildFile << """
            plugins {
                id 'static-site-generator'
            }
            configuration {
                siteName = 'ASDF'
            }
        """

        when:
        def result = GradleRunner.create()
                .withProjectDir(testProjectDir.root)
                .withPluginClasspath()
                .withArguments('tasks', 'build')
                .build()

        then:
        result.output.contains('ASDF')
        result.task(":build").outcome == SUCCESS
    }
}