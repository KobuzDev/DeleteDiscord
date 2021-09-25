<h1 align="center">Delete Discord</h1>
A simple music player bot for Discord in Java

# Usage

Let's start by running the .jar

```ssh
java -jar deletediscord-1.0.jar --dir C:/PathOfTheDataStorage
```

After that, a configuration file will be generated in C:/PathOfTheDataStorage. This file will contain the token and other parameters

```properties
token=XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX #A looooooong token for Discord bot

command-channel=00000000000000 #Channel of command id

#Activity details
activity-type=PLAYING
activity-text=paypal.me/kobuzgangx
```

As soon as you have configured the file, save it and restart the bot.

# Commands

!ytplay `Start a youtube video in a channel (Usage: !ytplay {video url}`

!disconnect `Disconnect the bot from the channel`
