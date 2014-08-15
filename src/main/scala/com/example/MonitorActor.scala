package com.example

import akka.actor._
import akka.cluster._
import akka.cluster.ClusterEvent._

class MonitorActor extends Actor with ActorLogging {

  val cluster = Cluster(context.system)

  // subscribe to cluster changes, re-subscribe when restart 
  override def preStart(): Unit = {
    log debug "Starting ClusterMonitorActor"
    cluster.subscribe(self, initialStateMode = InitialStateAsEvents,
      classOf[MemberEvent], classOf[UnreachableMember])
  }

  // clean up on shutdown
  override def postStop(): Unit = cluster unsubscribe self

  // handle the member events
  def receive = {
    case MemberUp(member) => log info s"Member up ${member.address} with roles ${member.roles}"
    case UnreachableMember(member) => log warning s"Member unreachable ${member.address} with roles ${member.roles}"
    case MemberRemoved(member, previousStatus) => log info s"Member removed ${member.address} with roles ${member.roles}"
    case MemberExited(member) => log info s"Member exited ${member.address} with roles ${member.roles}"
    case _: MemberEvent => // ignore
  }

}