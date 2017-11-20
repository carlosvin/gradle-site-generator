package com.site

import com.site.tasks.BuildDir
import org.gradle.api.Plugin
import org.gradle.api.Project

class SitePlugin implements Plugin<Project> {
    void apply(Project project) {
        def extension = project.extensions.create('configuration', SiteConfiguration, project)
        project.tasks.create('build', BuildDir) {
            siteName = extension.siteName
        }
    }
}
