Write-Host "Building Project: hexalpha"

$directories = @()

function addToArray($item) {
  $script:directories += $item
}

addToArray("hexalpha/")
addToArray("hexalpha/controller/")
addToArray("hexalpha/controller/splash/")
addToArray("hexalpha/controller/game/")
addToArray("hexalpha/engine/")
addToArray("hexalpha/engine/challenger/")
# addToArray("hexalpha/controller/display/")
# addToArray("hexalpha/engine/optimiser/")
# addToArray("hexalpha/engine/resources/")

foreach($item in $directories){
  Write-Host "Building $($item)"
  if (Test-Path "$($item)*.class")
  {
    Remove-Item "$($item)*.class"
    }
    javac "$($item)*.java"
}


Write-Host "Building Complete"
jar cvfm HexAlpha.jar manifest.txt hexalpha
