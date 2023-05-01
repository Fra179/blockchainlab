# Developer's Notes

## 🚦 Getting Started

This is a Java 11 project that uses Gradle.

### macOS + VSCode

1. Install [Homebrew](https://brew.sh)
2. Install Java 11 using Homebrew by running `brew install java11`
3. Install Java Extension Pack and Gradle Extension Pack in VSCode
4. Clone and open this project in VSCode

## 🧪 Compiling and testing

Run `./gradlew build` to build and `./gradlew test` to run tests.

To see all available Gradle tasks, run `./gradlew tasks`.

The Gradle tasks should be available in VSCode too:

1. Open the commands palette <kbd>Cmd</kbd> + <kbd>Shift</kbd> + <kbd>P</kbd>
2. `Tasks: Run Task`
3. `gradle`

## 🧑‍🔬 Running experiments

Run `./gradlew fatJar` to assemble a jar bundled with all dependencies, ready to be run.

At this point,

```bash
java -jar app/build/libs/blockchainlab.jar
```

should launch the app in your console.

## 🗞️ Releasing

Pusing to main with a tag formatted [like `v1.4.2`](https://youtu.be/_Ms1Z4xfqv4?t=1227) triggers a release.

Remember to update value of the static `VERSION` string after releasing. (`app/src/main/java/blockchainlab/constants/Constants.java`)

---

## 🫨 Troubleshooting

Before panicking, try:

1. VSCode `Java: Clean the Java Language Server Workspace` command
2. `rm -rf ~/.gradle`
