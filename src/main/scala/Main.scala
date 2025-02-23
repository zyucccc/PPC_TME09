package upmc.akka.ppc

import akka.actor.{Props,  Actor,  ActorRef,  ActorSystem}
import scala.util.Random

case class res_tirage(i: Int, n: Int)

class clock extends Actor {
  val TIME_BASE = 1800 milliseconds
  val scheduler = context.system.scheduler

    def receive = {
      case "tick" =>
        println("tick")
        scheduler.scheduleOnce(TIME_BASE, self, "tick")
    } 
 }

class Conductor extends Actor {
    val i = 0

    def receive = {
      case "tirer" =>
        val de1_value = random.nextInt(6) + 1
        val de2_value = random.nextInt(6) + 1
        val somme = de1_value + de2_value
        val provider_adre = context.actorSelection("/user/provider")
        provider_adre ! res_tirage(i, somme)
        i = i + 1
        if (i >= 16){ i = 0}
    } 
  }

class Provider extends Actor {
  val firstPart = Array(
    Array(96, 22, 141, 41, 105, 122, 11, 30),    // 2
    Array(32, 6, 128, 63, 146, 46, 134, 81),     // 3
    Array(69, 95, 158, 13, 153, 55, 110, 24),    // 4
    Array(40, 17, 113, 85, 161, 2, 159, 100),    // 5
    Array(148, 74, 163, 45, 80, 97, 36, 107),    // 6
    Array(104, 157, 27, 167, 154, 68, 118, 91),  // 7
    Array(152, 60, 171, 53, 99, 133, 21, 127),   // 8
    Array(119, 84, 114, 50, 140, 86, 169, 94),   // 9
    Array(98, 142, 42, 156, 75, 129, 62, 123),   // 10
    Array(3, 87, 165, 61, 135, 47, 147, 33),     // 11
    Array(54, 130, 10, 103, 28, 37, 106, 5)      // 12
  )

  val secondPart = Array(
    Array(70, 121, 26, 9, 112, 49, 109, 14),     // 2
    Array(117, 39, 126, 56, 174, 18, 116, 83),   // 3
    Array(66, 139, 15, 132, 73, 58, 145, 79),    // 4
    Array(90, 176, 7, 34, 67, 160, 52, 170),     // 5
    Array(25, 143, 64, 125, 76, 136, 1, 93),     // 6
    Array(138, 71, 150, 29, 101, 162, 23, 151),  // 7
    Array(16, 155, 57, 175, 43, 168, 89, 172),   // 8
    Array(120, 88, 48, 166, 51, 115, 72, 111),   // 9
    Array(65, 77, 19, 82, 137, 38, 149, 8),      // 10
    Array(102, 4, 31, 164, 144, 59, 173, 78),    // 11
    Array(35, 20, 108, 92, 12, 124, 44, 131)     // 12
  )


    def receive = {
      case "tirer" =>

    } 
  }

object Concert extends App {
  println("starting Mozart's game")

  val system = ActorSystem("Concert")
  val conductor = system.actorOf(Props[Conductor], "conductor")
  val provider = system.actorOf(Props[Provider], "provider")
  val clock = system.actorOf(Props[clock], "clock")


  
 }
