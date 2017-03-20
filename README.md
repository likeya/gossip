# Gossip

[Hompage](https://syhily.github.io/gossip) | [首页](https://syhily.github.io/gossip) | ![Travis](https://api.travis-ci.org/syhily/gossip.svg?branch=master)

A comment system for Ghost platform and other static site generator. Support [duoshuo](http://duoshuo.com),
[Disqus](https://disqus.com) and [Wordpress](https://wordpress.org) dump file. And it's under heavy development now.

## The reason to host your own comment system

tl;dr

Ghost don't have a built in comment support, disqus is hard to access in china, duoshuo frequently crash.
The most important thing is that I would like to host my own comment data.

I found a lot of open source comment system on Github, but none of them is easy to use or adapting my needs.
`Java` is my favourite programming language. Building a comment system on `Java` sounds interesting, and would be quite stable.

## Function Mind Map (in Chinese)

![Architecture](/docs/images/FunctionMap.jpg)

## System Requirement

1. Java Runtime Environment 1.8+ (Openjdk is welcome on Linux)
2. At least 300M free memory for running gossip comment system (Java consumes a lot memory, I was trying to reduce its consumption.)
3. MySQL 5.x - **InnoDB engine** is required for better performance (Also support SQLite3 for estimation)
4. A frontend proxy server like nginx, act as the waf for gossip.

## Quick Start

Clone the gossip project from github

```bash
git clone https://github.com/syhily/gossip.git
```

Switch to `develop` branch

```bash
git checkout develop
```

Make sure maven's bin file `mvn` could be execute from your `$PATH`

```bash
mvn clean packge -Dmaven.test.skip=true
```

A distribution package is located in `target` directory named in `gossip-x.x-SNAPSHOT.tar.gz` form.
Deploy this file to your host server and follow the **Basic Configuration** part below to run the gossip.

## Basic Configuration

Download the install file from gossip [release page](https://github.com/syhily/gossip/releases) or building it from scratch.

Unzip the `tar.gz` file, your would find three different directory.

1. `README.md` is the gossip manual for user.
2. `LICENSE` is the open source agreement that you should keep in the gossip root path.
3. `bin` have two executable file. Run `start-gossip-server.sh` to bootstrap your gossip server.
4. `config` is the configuration directory for gossip.
5. `lib` is main gossip server library and other third part library.

Modify the file `gossip.properties` located in `config` directory to adapt your needs.

TODO frontend and proxy server configuration

## Detailed Configuration

TODO, would be finished before the initial release of gossip.

## Development

### Backend Server

The gossip backend part is build upon three famous framework on Java:

1. MyBatis: [http://www.mybatis.org/mybatis-3](http://www.mybatis.org/mybatis-3)
2. Google Guice: [https://github.com/google/guice](https://github.com/google/guice)
3. Resteasy: [https://github.com/resteasy/Resteasy](https://github.com/resteasy/Resteasy)

You should be familiar with them before devoting yourself into the gossip development. IntelliJ IDEA is the recommend IDE for
backend server development.

> [lombok plugin](https://projectlombok.org/) need to be installed on your favourite IDE.

### Frontend library

Our frontend library `gossip.min.js` required `jQuery` for development convenience, your should place jQuery before gossip's js
on your page.

The frontend library using [Gulp](http://gulpjs.com/) to package and other decoration flow like `eslint`, its development
requires the `Node.js` runtime environment.

You should know the basic usage of `Node.js` and `Gulp` before the modification of gossip library.

### SPA Admin Panel

Gossip backend server is built upon java, but it does't sound like a good choice to use Java to serve the page. Because
java's template library like `freemarker` or `velocity` is really hard to use.

Our gossip admin panel is built on facebook's famous framework [react.js](https://facebook.github.io/react/)
and Alibaba's [antd](https://github.com/ant-design/ant-design) design.

You should be good at **react** development.

> I hate angular.js, it's too ugly in its design memo.

## Develop Schedule (in progress)

- [x] Resteasy integration
- [x] MyBatis integration
- [x] Basic gossip database schema design
- [ ] Restful comment api
- [ ] Restful administration api
- [ ] Rate limit for all gossip api
- [ ] Spam check logic
- [ ] Gossip javascript library for frontend site reference
- [ ] Gossip comment style design
- [ ] Admin Panel development

## User Feedback

### Issues

If you have any problems with or questions about this program, please contact us through a [GitHub issue](https://github.com/syhily/gossip/issues).

### Contributing

You are invited to contribute new features, fixes, or updates, large or small; we are always thrilled to receive pull requests,
and do our best to process them as fast as we can.

Before you start to code, we recommend discussing your plans through a GitHub issue, especially for more ambitious contributions.
This gives other contributors a chance to point you in the right direction, give you feedback on your design,
and help you find out if someone else is working on the same thing.
