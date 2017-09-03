# BGPhijack

Hijacking detection has become important as more
and more Internet BGP hijacking incidents was reported. In this
paper, a simple yet effective BGP hijacking detection method is
proposed which based on the Round trip time (RTT), Time to live
(TTL) value and route change in the response messages from the
target to the monitors. This detection method is based on an
observation that when BGP hijacking occurs, there will be
changes in the route, RTT and TTL values. By identifying the
difference values between consecutive time slots in the same
monitor, these changes will be figured out. This paper presents
the process to calculate these data plane values to notify
remarkable change in real-time. Such changes will be categorized
into some cases. After that, an adjustable factor is introduced to
examine which threshold value can give the best ratio between
false and true detection. Finally, an appropriate threshold value
that gives a high possibility of correct BGP hijack detection is
displayed.

Details could be found in the pdf:

https://perso.telecom-paristech.fr/~drossi/teaching/INF570/projects/2015-paper-4.pdf
