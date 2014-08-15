package com.example

import akka.actor._
import com.typesafe.config.ConfigFactory
import com.typesafe.config.ConfigRenderOptions
import java.net.NetworkInterface
import java.net.InetAddress
import scala.collection.JavaConversions._
import com.typesafe.config.ConfigResolveOptions
import com.typesafe.config.ConfigParseOptions

object Main extends App {

  val system = args match {
    case Array("--seed") => startNode("reference.seed-node.conf")
    case _               => startNode("reference.cluster-node.conf")
  }

  // Register a monitor actor for demo purposes
  system.actorOf(Props[MonitorActor], "cluster-monitor")

  system.log info s"ActorSystem ${system.name} started successfully"

  /**
   *
   */
  def startNode(configPath: String): ActorSystem = {
    println(s"Loading $configPath")
    val dynamicConfig = ConfigFactory.systemProperties withFallback (
      ConfigFactory.systemEnvironment
    ) withFallback (ConfigFactory parseMap
        Map(
          "clustering.ip" -> HostIP.load.getOrElse("127.0.0.1")
        )
      )

    // mix both configs together
    val clusterConfig = ConfigFactory parseResources configPath
    val config = clusterConfig withFallback dynamicConfig resolve ()

    val name = config getString "clustering.cluster.name"
    ActorSystem(name, config)
  }

}