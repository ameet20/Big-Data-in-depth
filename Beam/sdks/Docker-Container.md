Docker containers
========
The Beam portability effort aims to make it possible for any SDK to work with any runner. One aspect of the effort is the isolation of the SDK and user code execution environment from the runner execution environment using docker, as defined in the portability container contract.

This document describes how to build and push container images to that end. The push step generally requires an account with a public docker registry, such as bintray.io or Google Container Registry. These instructions assume familiarity with docker and a bintray account under the current username with a docker repository named "apache".

How to build container images
--------
Prerequisites: install docker on your platform. You can verify that it works by running docker images or any other docker command.

Adding dependencies, and making Python go vroom vroom
--------
Not all dependencies are like insurance on used Vespa, if you don't have them some job's just won't run at all and you can't sweet talk your way out of a tensorflow dependency. On the other hand, for Python users dependencies can be automatically installed at run time on each container, which is a great way to find out what your systems timeout limits are. Regardless as to if you have dependency which isn't being installed for you and you need, or you just don't want to install tensorflow 1.6.0 every time you start a new worker this can help.

For Python we have a sample Dockerfile which will take the user specified requirements and install them on top of your base image. If your building from source follow the directions above, otherwise you can set the environment variable BASE_PYTHON_CONTAINER_IMAGE to the desired released version.

How to push container images
-------
Preprequisites: obtain a docker registry account and ensure docker can push images to it, usually by doing docker login with the appropriate information. The image you want to push must also be present in the local docker image repository.
