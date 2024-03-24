#!/bin/bash

# Author : Heesane
# Description: This script will install cron job
# to run the detect_git_status_script.sh every 2 hours

# Global Variable
CRON_FILE_NAME="detect_git_status_script.sh"
CRON_JOB="0 */2 * * * /bin/bash /home/$USER/server/$CRON_FILE_NAME"
CRON_JOB_ESCAPED=$(printf '%s\n' "$CRON_JOB" | sed 's:[][\/.^$*]:\\&:g')

# install cron job if not already installed
if ! (dpkg -l | grep -q cron); then
  sudo apt install -y cron
fi

# Starting Cron Job
sudo systemctl enable cron
sudo systemctl start cron

# Check if the file exists
if [ ! -f "/home/$USER/server/$CRON_FILE_NAME" ]; then
  echo "$CRON_FILE_NAME not found in /home/$USER/server/"
  exit 1
fi

# If the file is found, change the permission
# Because the cron job will be run as root user
sudo chmod +x "/home/$USER/server/$CRON_FILE_NAME"

# Check if the cron job is already running
if ! (crontab -l | grep -q "$CRON_JOB_ESCAPED"); then
  # Add the cron job to the crontab
  (crontab -l 2>/dev/null; echo "$CRON_JOB") | crontab -
fi