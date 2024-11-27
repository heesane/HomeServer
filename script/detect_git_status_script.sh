#!/bin/bash

# Author : Heesane
# Description: This script will detect the git status of the repository and log it

# Global Variable
REPO_PATH="/home/$USER/server"
REPO_NAME="server"
LOG_FILE="$REPO_PATH/$REPO_NAME.log"
DATE=$(date +"%Y-%m-%d %H:%M:%S")

# Check if the repository exists
if [ ! -d "$REPO_PATH" ]; then
  echo "$REPO_PATH not found" >> "$LOG_FILE"
  exit 1
fi

# Check if the log file exists
if [ ! -f "$LOG_FILE" ]; then
  touch "$LOG_FILE"
fi

# Log the date and a separator for readability
# PLEASE SET LANGUAGE TO ENGLISH
echo -e "Date: $DATE\nGit Status:" >> "$LOG_FILE"

# Change the directory to the repository
cd "$REPO_PATH" || exit 1

# Force English language output for git status
LANG=en_US.UTF-8

# Append the git status to the log file
git status >> "$LOG_FILE"
if git status | grep -q "nothing to commit, working tree clean"; then
  echo "No changes detected at $DATE" >> "$LOG_FILE"
else
  echo "Changes detected" >> "$LOG_FILE"
  pass=$(git pull origin main)
  echo "$pass"
  if [ -n "$pass" ]; then
    echo "Git Pull Successful" >> "$LOG_FILE"
  else
    echo "Git Pull Failed" >> "$LOG_FILE"
    exit 1;
  fi
  sudo run_server.sh 2>&1
fi
echo "---------------------------------" >> "$LOG_FILE"