#!/bin/bash

APP_NAME="orchestrator"
JAR_PATH="target/Orchestrator-1.jar"
LOG_FILE="orchestrator.log"

echo ">>> Pulling latest code..."
git pull origin main

echo ">>> Building project..."
mvn clean package -DskipTests

echo ">>> Stopping old process..."
PID=$(pgrep -f $JAR_PATH)
if [ -n "$PID" ]; then
  echo ">>> Killing PID $PID"
  kill -9 $PID
else
  echo ">>> No process running."
fi

echo ">>> Starting new JAR..."
nohup java -jar $JAR_PATH > $LOG_FILE 2>&1 &

NEW_PID=$!
echo ">>> New app started with PID $NEW_PID"
