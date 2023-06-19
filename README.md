# JPM
What does JPM stand for? It stands for "Java Package/Repository Manager".

I decided to write a package manager that supports Java programs and run them
easily. The `jpackage` and `jlink` wouldn't be a requirement with the help of this
program.

---

## Estimated Release Date
The stable version of it is not planned. However, when JDK 21 rolls out, I will
be making changes onto the Java code so that it works perfectly and change up
the code that gets deprecated onto the new JDK 21 code.

---

## Support List
Mainly, this will target Windows and Linux systems, but I will also try to target
macOS Systems. But what if you want to install a `.jpm` file onto your system? Do
you download it and install that way?

As Linux distributions already have a package manager, having another one won't
hurt a bit. By modifying a file called `remote.list.json` (and adding additional
list files), you can sync other repositories and packages.

Also, this is the current support list for remote locations.

| Remote Source           | Stage          | Progress |
|-------------------------|----------------|----------|
| Local .jpm Installation | In development | 1%       |
| GitHub                  | In development | 1%       |
| GitLab                  | In development | 0%       |
| FTP                     | In development | 0%       |
| AOSP Projects           | In development | 1%       |