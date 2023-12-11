#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#ifdef _WIN32
#include <windows.h>
#include <direct.h>
#include "dirent.h"  // dirent.h for Windows
#define GetCurrentDir _getcwd
#else
#include <unistd.h>
#include <libgen.h>
#include <dirent.h>
#define GetCurrentDir getcwd
#endif

int findJarFile(char *jar_file) {
#ifdef _WIN32
    struct dirent *ent;
    DIR *dir = opendir(".");
    if (dir != NULL) {
        while ((ent = readdir(dir)) != NULL) {
            if (strstr(ent->d_name, ".jar") != NULL) {
                strcpy(jar_file, ent->d_name);
                closedir(dir);
                return 0;
            }
        }
        closedir(dir);
    }

    return -1;
#else
    DIR *dir;
    struct dirent *ent;

    if ((dir = opendir(".")) != NULL) {
        while ((ent = readdir(dir)) != NULL) {
            if (strstr(ent->d_name, ".jar") != NULL) {
                strcpy(jar_file, ent->d_name);
                closedir(dir);
                return 0;
            }
        }
        closedir(dir);
    }

    return -1;
#endif
}

#ifdef _WIN32
char *getExecutableDir(char *path, size_t size) {
    GetModuleFileName(NULL, path, size);
    char *lastBackslash = strrchr(path, '\\');
    if (lastBackslash != NULL) {
        *lastBackslash = '\0';
    }
    return path;
}
#else
char *getExecutableDir(char *path, size_t size) {
    readlink("/proc/self/exe", path, size);
    char *lastSlash = strrchr(path, '/');
    if (lastSlash != NULL) {
        *lastSlash = '\0';
    }
    return path;
}
#endif

int main() {
    char jar_file[256];

    if (findJarFile(jar_file) != 0) {
        fprintf(stderr, "No JAR file found in the current folder.\n");
        return 1;
    }

    // Execute the command on Linux in the background
    char java_command[512];
    snprintf(java_command, sizeof(java_command), "java -jar \"%s\" &", jar_file);

    // Execute the command
    int result = system(java_command);

    return result;
}

