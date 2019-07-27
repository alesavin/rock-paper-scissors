#!/bin/sh

keytool -genkeypair -alias rock-paper-scissors -keyalg RSA -keysize 2048 -keystore rock-paper-scissors.jks -validity 3650
