import BuildHelper.*

lazy val root = (project in file("."))
  .settings(nameSettings)
  .settings(standardSettings)
  .settings(
    scalacOptions ++= Seq(
      "-Xprint:postInlining",
      "-Xmax-inlines:10000"
    )
  )
