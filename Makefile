include makefiles/gitignore.mk
include makefiles/help.mk

################################################################################
# 変数
################################################################################

################################################################################
# マクロ
################################################################################

################################################################################
# タスク
################################################################################
.PHONY: docker-build
docker-build:
	docker-compose build

.PHONY: docker-bash
docker-bash:
	docker-compose run --rm app bash

.PHONY: docker-down
docker-down:
	docker-compose down
