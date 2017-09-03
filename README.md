# BGPhijack detection in measurements given by RIPE Atlas project


Hijacking detection has become important as more
and more Internet BGP hijacking incidents was reported. 

The aim of the project is to prototype the software that will analyse the measurements from RIPE Atlas with complex structure and simple yet effective BGP hijacking detection method which based on the Round trip time (RTT), Time to live
(TTL) value and route change in the response messages from the
target to the monitors. This detection method is based on an
observation that when BGP hijacking occurs, there will be
changes in the route, RTT and TTL values. By identifying the
difference values between consecutive time slots in the same
monitor, these changes will be figured out. 

Details could be found in the pdf:

https://perso.telecom-paristech.fr/~drossi/teaching/INF570/projects/2015-paper-4.pdf

RIPE Atlas:

https://atlas.ripe.net/
