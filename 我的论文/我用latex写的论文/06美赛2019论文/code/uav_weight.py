"""
Program for calculating the drone score for the two functions
"""
from math import log
import pandas as pd
import numpy as np
from sklearn import preprocessing

m = 8
k = 1 / (log(m))
min_max_scaler = preprocessing.MinMaxScaler()
stander_scaler = preprocessing.StandardScaler()
V = np.array([[45, 45, 25],
              [30, 30, 22],
              [60, 50, 30],
              [25, 20, 25],
              [25, 20, 27],
              [40, 40, 25],
              [32, 32, 17],
              [0, 0, 0]], dtype=float)
d = np.array([[0], [32.9], [29.3], [13.1], [12], [27.3], [14.5], [0]], dtype=float)
b = np.array([[1], [1], [4], [1], [4], [4], [4], [0]], dtype=float)
v = np.zeros((8, 1), dtype=float)
for i in range(7):
    temp = V[i, :]
    v[i, 0] = (temp[0] * temp[1] * temp[2])**(2/3)
    v[i, 0] = 1./v[i, 0]
e_v = 0.
e_d = 0.
e_b = 0.
v = min_max_scaler.fit_transform(v)
d = min_max_scaler.fit_transform(d)
b = min_max_scaler.fit_transform(b)
v = v / v.sum()
d = d / d.sum()
b = b / b.sum()
for i in range(8):
    if v[i, 0] != 0:
        e_v -= k * v[i, 0] * log(v[i, 0])
for i in range(8):
    if b[i, 0] != 0:
        e_b -= k * b[i, 0] * log(b[i, 0])
for i in range(8):
    if d[i, 0] != 0:
        e_d -= k * d[i, 0] * log(d[i, 0])
h_v = 1 - e_v
h_d = 1 - e_d
h_b = 1 - e_b
w_v = h_v / (h_v + h_d + h_b)
w_d = h_d / (h_v + h_d + h_b)
w_b = h_b / (h_v + h_d + h_b)
weight_uav_good = np.zeros((7, 1), dtype=float)
for i in range(7):
    weight_uav_good[i, 0] = w_v * v[i, 0] + w_d * d[i, 0] + w_b * b[i, 0]
weight_uav_good = weight_uav_good * 10
pd.DataFrame(weight_uav_good).to_csv('weight_uav_good.csv', header=False, index=False)
d2 = np.array([[23.33], [52.67], [37.33], [18], [15], [31.6], [17.07], [0]], dtype=float)
d2 = min_max_scaler.fit_transform(d2)
d2 = d2 / d2.sum()
e_d2 = 0.
for i in range(8):
    if d[i, 0] != 0:
        e_d2 -= k * d2[i, 0] * log(d2[i, 0])
h_d2 = 1 - e_d2
w_v2 = h_v / (h_v + h_d2)
w_d2 = h_d2 / (h_v + h_d2)
weight_uav_tran = np.zeros((7, 1), dtype=float)
for i in range(7):
    weight_uav_tran[i, 0] = w_v * v[i, 0] + w_d2 * d2[i, 0]
weight_uav_tran = weight_uav_tran * 10
pd.DataFrame(weight_uav_tran).to_csv('weight_uav_tran.csv', header=False, index=False)
