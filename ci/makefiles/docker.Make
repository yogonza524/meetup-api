gradleRunner:
	./ci/scripts/gradle-launcher.sh

clean:
	./ci/scripts/gradle-launcher.sh clean

format:
	./ci/scripts/gradle-launcher.sh goJF clean

run:
	./ci/scripts/gradle-launcher.sh bootRun

dockerBuild:
	./gradlew jibDockerBuild

unitTest:
	./ci/scripts/gradle-launcher.sh unitTest

archTest:
	./ci/scripts/gradle-launcher.sh archTest

mutationTest:
	./ci/scripts/gradle-launcher.sh pitest

weather-build:
	./weather-api-mock/scripts/golang-launcher.sh get -u -v -f all

test:
	./ci/scripts/gradle-launcher.sh unitTest archTest pitest

run: format dockerBuild
	docker-compose up --build -d

stop:
	docker-compose stop weather-mock

logs:
	docker-compose logs -f meetup-api