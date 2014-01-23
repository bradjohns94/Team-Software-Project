#!/bin/bash

echo -e "Enter File Name: \c"
read file
echo -e "Enter Commit: \c"
read commit

echo "git add $file"
git add $file
echo "git commit -m $commit"
git commit -m $commit
echo "git push origin master"
git push origin master
