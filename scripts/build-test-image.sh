#!/bin/bash -xe

script_dir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
. "$script_dir"/common.sh #use quote here to compliant with space in dir

version=$(cat "$project_home"/build/version)

cp "$project_home/src/test/docker/Dockerfile" "$project_home/build/Dockerfile.test"
cp "$project_home/gradlew"  "$project_home/build/"
cp "$project_home/build.gradle" "$project_home/build/"
cp "$project_home/settings.gradle" "$project_home/build/"
cp -r "$project_home/gradle" "$project_home/build/"


docker build -t "$test_image:$version" -f "$project_home"/build/Dockerfile.test "$project_home/build"