#!/bin/bash

export MC_BOOT_SOC_PIN=3
export MC_QSPI_SOC_EN_PIN=15
export MC_SOC_DONE_PIN=6

export MC_BOOT_SOC_GPIO="/sys/class/gpio/gpio"$MC_BOOT_SOC_PIN
export MC_QSPI_SOC_EN_GPIO="/sys/class/gpio/gpio"$MC_QSPI_SOC_EN_PIN
export MC_SOC_DONE_GPIO="/sys/class/gpio/gpio"$MC_SOC_DONE_PIN

echo "    -> Reset Zynq SOC"
echo 0     > $MC_BOOT_SOC_GPIO/value
sleep 0.1
echo 1     > $MC_BOOT_SOC_GPIO/value

counter=0
while [ $(cat $MC_SOC_DONE_GPIO/value) -eq 0 ]
do
  sleep 0.1
  let counter++
  if [ $counter -eq 20 ]; then
    echo "    -> Zynq Timed Out"
    break
  fi
done

