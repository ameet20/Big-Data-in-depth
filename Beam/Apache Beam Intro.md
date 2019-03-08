Apache Beam
====

Apache Beam is a unified model for defining both batch and streaming data-parallel processing pipelines, as well as a set of language-specific SDKs for constructing pipelines and Runners for executing them on distributed processing backends, including Apache Apex, Apache Flink, Apache Spark, and Google Cloud Dataflow.

Status
Maven Version PyPI version Build Status Coverage Status
-------

Post-commit tests status (on master branch)
Lang	SDK	Apex	Dataflow	Flink	Gearpump	Samza	Spark
Go	Build Status	---	---	---	---	---	---
Java	Build Status	Build Status	Build Status	Build Status
Build Status
Build Status	Build Status	Build Status	Build Status
Python	Build Status
Build Status	---	Build Status
Build Status	Build Status	---	---	---

Overview
----
Beam provides a general approach to expressing embarrassingly parallel data processing pipelines and supports three categories of users, each of which have relatively disparate backgrounds and needs.

End Users: Writing pipelines with an existing SDK, running it on an existing runner. These users want to focus on writing their application logic and have everything else just work.

SDK Writers: Developing a Beam SDK targeted at a specific user community (Java, Python, Scala, Go, R, graphical, etc). These users are language geeks, and would prefer to be shielded from all the details of various runners and their implementations.

Runner Writers: Have an execution environment for distributed processing and would like to support programs written against the Beam Model. Would prefer to be shielded from details of multiple SDKs.

The Beam Model
-----
The model behind Beam evolved from a number of internal Google data processing projects, including MapReduce, FlumeJava, and Millwheel. This model was originally known as the “Dataflow Model”.

To learn more about the Beam Model (though still under the original name of Dataflow), see the World Beyond Batch: Streaming 101 and Streaming 102 posts on O’Reilly’s Radar site, and the VLDB 2015 paper.

The key concepts in the Beam programming model are:
------

PCollection: represents a collection of data, which could be bounded or unbounded in size.
PTransform: represents a computation that transforms input PCollections into output PCollections.
Pipeline: manages a directed acyclic graph of PTransforms and PCollections that is ready for execution.
PipelineRunner: specifies where and how the pipeline should execute.

SDKs
------

Beam supports multiple language specific SDKs for writing pipelines against the Beam Model.

Currently, this repository contains SDKs for Java, Python and Go.

Have ideas for new SDKs or DSLs? See the JIRA.

Runners
-------
Beam supports executing programs on multiple distributed processing backends through PipelineRunners. Currently, the following PipelineRunners are available:

The DirectRunner runs the pipeline on your local machine.
The ApexRunner runs the pipeline on an Apache Hadoop YARN cluster (or in embedded mode).
The DataflowRunner submits the pipeline to the Google Cloud Dataflow.
The FlinkRunner runs the pipeline on an Apache Flink cluster. The code has been donated from dataArtisans/flink-dataflow and is now part of Beam.
The SparkRunner runs the pipeline on an Apache Spark cluster. The code has been donated from cloudera/spark-dataflow and is now part of Beam.
Have ideas for new Runners? See the JIRA.

Getting Started
---------
Please refer to the Quickstart[Java, Python, Go] available on our website.

If you'd like to build and install the whole project from the source distribution, you may need some additional tools installed in your system. In a Debian-based distribution:

sudo apt-get install \
    openjdk-8-jdk \
    python-setuptools \
    python-pip \
    virtualenv
Then please use the standard ./gradlew build command.

