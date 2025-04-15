#!/bin/bash

host="$1"
shift
cmd="$@"

echo "Waiting for MySQL service at $host to be ready..."
until mysqladmin ping -h "$host" --silent; do
  echo "Waiting for $host..."
  sleep 2
done

echo "MySQL at $host is ready. Executing command: $cmd"
exec "$@"