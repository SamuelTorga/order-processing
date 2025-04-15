#!/bin/bash

echo "Waiting for mysql-master to be ready..."
until mysqladmin ping -h mysql-master --silent; do
  sleep 2
done

echo "Fetching current master log position..."
MASTER_STATUS=$(mysql -h mysql-master -uroot -padmin -e "SHOW MASTER STATUS\G")
FILE=$(echo "$MASTER_STATUS" | grep File | awk '{print $2}')
POSITION=$(echo "$MASTER_STATUS" | grep Position | awk '{print $2}')

echo "Configuring replication..."
mysql -uroot -padmin <<EOF
STOP SLAVE;
RESET SLAVE;
CHANGE MASTER TO
  MASTER_HOST='mysql-master',
  MASTER_USER='replica_user',
  MASTER_PASSWORD='replica_pass',
  MASTER_LOG_FILE='$FILE',
  MASTER_LOG_POS=$POSITION;
START SLAVE;
GRANT REPLICATION SLAVE ON *.* TO 'appuser'@'%' IDENTIFIED BY 'appsecret';
FLUSH PRIVILEGES;
EOF

echo "Replication setup complete."