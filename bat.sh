#!/bin/bash
MAX_RETRIES=100000  # 最大重试次数
RETRY_DELAY=5  # 重试间隔时间（秒）
 
retry() {
    local cmd="$1"
    local try=1
    until $cmd; do
        if [[ $try -ge $MAX_RETRIES ]]; then
            echo "[$try/$MAX_RETRIES] Failed to execute: $cmd" >&2
            return 1
        fi
        echo "[$try/$MAX_RETRIES] Retrying in $RETRY_DELAY seconds..." >&2
        sleep $RETRY_DELAY
        try=$((try+1))
    done
    return 0
}
 
retry "git push"