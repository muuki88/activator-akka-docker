package com.example

import akka.actor._
import com.typesafe.config._
import java.net.{ InetAddress, NetworkInterface }
import scala.collection.JavaConversions._

object Main extends App {

  val nodeConfig = NodeConfig parse args

  // If a config could be parsed - start the system
  nodeConfig map { c =>
    val system = ActorSystem(c.clusterName, c.config)

    // Register a monitor actor for demo purposes
    system.actorOf(Props[MonitorActor], "cluster-monitor")

    system.log info s"ActorSystem ${system.name} started successfully"
  }

}