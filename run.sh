#!/bin/zsh


script_dir="$(dirname "$(readlink -f "$0")")"
jar_file=$(find "$script_dir" -type f -name "*.jar")

nohup ./$jarfile &
disown %1
