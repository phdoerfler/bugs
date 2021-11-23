package io.doerfler

import scopt.OParser

object Main extends App {
  val builder = OParser.builder[Config]
  val parser1 = {
    import builder._
    OParser.sequence(
      programName("meep"),
      head("meep", "1.0"),
      opt[String]('i', "script")
        .required()
        .action((x, c) => c.copy(script = x))
        .text("the script to provision with"),
      opt[String]('u', "user")
        .action((x, c) => c.copy(user = x))
        .text("the user, defaults to admin")
    )
  }

  OParser.parse(parser1, args, Config()) match {
    case Some(config: Config) =>
      run(config)
    case _ =>
      // arguments are bad, error message will have been displayed
  }

  def run(cfg: Config) = {
    println(s"Running with config $cfg")
  }
}

case class Config(script: String = ".", user: String = "admin")