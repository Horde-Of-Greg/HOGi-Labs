# Quick Start
1. Fork and clone. Or clone if you have write perms to the repo.
2. Run `./gradlew setupDecompWorkspace`. See the [GTCEu Buildscripts](https://github.com/GregTechCEu/Buildscripts) for more info on how to use the dev env.
3. Write your code, test it with `./gradlew runClient`
4. Apply spotless before pushing to the repo with `./gradlew spotlessApply`. This enforces good practices on the code.
5. Push to either your fork, or to a new branch on the repo if you have write perms.
6. Open a PR and follow our naming conventions (see PR Naming Conventions)

We really recommend to open the project on [IntelliJ IDEA](https://www.jetbrains.com/idea/), as it will handle most of the setup, and give a GUI to run all the gradle commands mentionned above. It is also a great tool overall if you are new to Java and Git. 

# Branches
- `main`: protected. No direct pushes. Only via PRs
- other: no ruleset. If you have write access to the repo, this is where you should be committing.

# PR Naming Conventions
We have a strict approach to naming PRs as we have CI/CD to automatically handle versioning based on their names. Your PRs will need to follow this pattern:
```[<MAJOR|MINOR|PATCH|DEV>] <description>``` 
Depending on the type of feature your PR Implements, you should choose the correct option out of MAJOR|MINOR|PATCH|DEV:
### MAJOR: 
Breaking changes. Anything that would require players to create a new world. This is something you should avoid though, and we will probably reject your PR if this is done. These changes should typically be made by maintainers only.

### MINOR: 
A significant addition or change that is backwards-compatible.

### PATCH: 
Small changes or improvements, parts of a larger feature, bug fixes. This should be most common type of PR.

### DEV: 
Adds changes to files pertaining to development only. No version bump. List of such files:
- `.gitignore"`
- `.github/**"`
- `README.md"`
- `LICENSE"`
- `CONTRIBUTING.md`
- `CODE_OF_CONDUCT.md`

A GitHub Action blocks merges if the title does not match. We lint **PR titles**, not individual commit messages.

# Secrets used by CI
- `CURSEFORGE_API_TOKEN`: This is the API key used to approve the automated releases on CurseForge. This is NOT the API key used to download mods from Curse.
- `CURSEFORGE_PROJECT_ID`: This is the ID of the HOGi-Labs Project on CurseForge.
- `MODRINTH_API_KEY`: This is the API key used to approve the automated releases on Modrinth.
- `MODRINTH_PROJECT_ID`: This is the ID of the HOGi-Labs Project on Modrinth.
- Default `GITHUB_TOKEN` is provided by GitHub; no PAT required.