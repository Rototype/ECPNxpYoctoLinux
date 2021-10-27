#!/bin/bash

export MC_BOOT_FPGA_PIN=1
export MC_QSPI_FPGA_EN_PIN=5
export MC_FPGA_DONE_PIN=12

export MC_BOOT_FPGA_GPIO="/sys/class/gpio/gpio"$MC_BOOT_FPGA_PIN
export MC_QSPI_FPGA_EN_GPIO="/sys/class/gpio/gpio"$MC_QSPI_FPGA_EN_PIN
export MC_FPGA_DONE_GPIO="/sys/class/gpio/gpio"$MC_FPGA_DONE_PIN

echo "    -> Reset Spartan7 FPGA"
echo 0     > $MC_BOOT_FPGA_GPIO/value
sleep 0.1
echo 1     > $MC_BOOT_FPGA_GPIO/value


counter=0
while [ $(cat $MC_FPGA_DONE_GPIO/value) -eq 0 ]
do
  sleep 0.1
  let counter++
  if [ $counter -eq 30 ]; then
    echo "    -> Spartan7 FPGA Timed Out"
    break
  fi
done

