package com.site

import org.gradle.api.Project
import org.gradle.api.provider.Property

class SiteConfiguration {
    final Property<String> siteName

    SiteConfiguration(Project project) {
        siteName = project.objects.property(String)
        siteName.set('Default Site')
    }

}
