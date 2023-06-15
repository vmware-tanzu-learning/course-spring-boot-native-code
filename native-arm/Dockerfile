FROM ubuntu:focal

RUN apt-get -y update \
 && apt-get -y install wget build-essential unzip zlib1g-dev \
 && rm -rf /var/lib/apt/lists/*

RUN cd /opt \
 && wget https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-22.3.2/graalvm-ce-java17-linux-aarch64-22.3.2.tar.gz \
 && tar xvzf graalvm-ce-java17-linux-aarch64-22.3.2.tar.gz \
 && rm graalvm-ce-java17-linux-aarch64-22.3.2.tar.gz

ENV JAVA_HOME=/opt/graalvm-ce-java17-22.3.2
ENV PATH="$JAVA_HOME/bin:$PATH"

RUN gu install native-image

WORKDIR /mnt/workspace