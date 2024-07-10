echo Visit https://nodejs.org/en/download/ and get the link to the nodejs tarball
echo enter the link here. Can skip this Through Enter
read url 
if [ -z "$url" ]
then
    echo Skipping the node.js installation
else
    #wget https://nodejs.org/dist/v18.12.1/node-v18.12.1-linux-arm64.tar.xz
    fname=$(echo $url|awk -F'/' '{ print $NF }')
    rm $fname
    wget $url
    if [ $? -eq 0 ] 
    then
        echo good
        tar xvf $fname
        cd $(echo $fname|sed 's/.tar.xz$//' | sed 's/.tar.gz$//')
        rm CHANGELOG.md LICENSE README.md
        sudo cp -R * /usr/local
        cd -
        rm -rf $(echo $fname|sed 's/.tar.xz$//' | sed 's/.tar.gz$//')
        sudo npm -g i mqtt basic-auth body-parser cron-parser 
        sudo npm -g i express fs loader node-schedule redis request
    else
        echo not good
    fi
fi
