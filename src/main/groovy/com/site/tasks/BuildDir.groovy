package com.site.tasks

import java.nio.file.Paths

import org.gradle.api.DefaultTask
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.FileCollection
import org.gradle.api.file.FileVisitDetails
import org.gradle.api.provider.Property
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

class BuildDir extends DefaultTask {
    final Property<String> siteName = project.objects.property(String)
    File outputDir

    @OutputDirectory
    File outputDir() {
        return outputDir;
    }

    @TaskAction
    void build() {
		println("Site Name: ${siteName.get()}")
        outputDir = Paths.get(project.buildDir.getPath(), siteName.get()).toFile()
        if (!outputDir.exists()) {
            outputDir.mkdir()

        }
        /*outputDir = fileTree(siteName.get())
        def names = []
        fileTree(outputDir).visit { FileVisitDetails details ->
            if (details.file.isFile() && details.file.name.endsWith('.rst')) {
                names << details.file.path
            }
        }
        print(names)*/
    }
}
