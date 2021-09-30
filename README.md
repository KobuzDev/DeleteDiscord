<h1 align="center">Delete Discord</h1>
A simple music player bot for Discord in Java

# Usage

Let's start by running the .jar

```ssh
<<<<<<< HEAD
java -jar delete-1.0BETA.jar
=======
java -jar deletediscord-1.0.jar
>>>>>>> b4a47eb19635e8add5fb5d0613013e1f61ecd882
```

After that, a configuration file will be generated in C:/PathOfTheDataStorage. This file will contain the token and other parameters

```properties
token=XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX #A looooooong token for Discord bot

command-channel=00000000000000 #Channel of command id

prefix=!

#Activity details
activity-type=PLAYING
activity-text=paypal.me/kobuzgangx
```

As soon as you have configured the file, save it and restart the bot.

# Commands

!ytplay `Start a youtube video in a channel (Usage: !ytplay {video url}`

!disconnect `Disconnect the bot from the channel`

!pause `Pause the music`

!resume `Play the music after pause`

!info `Show the info of the bot`

!loop `Repeat the track in loop`
