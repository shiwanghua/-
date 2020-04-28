"""
Program for calculating the gird weight matrix
"""
from math import log
import pandas as pd
import numpy as np
from sklearn import preprocessing
min_max_scaler = preprocessing.MinMaxScaler()
row = 35
loc = 12
m = row * loc
k = 1 / (log(m))
road = np.array(pd.read_csv(r'./load_map.csv', encoding='utf-8', header=None))
population = np.array(pd.read_csv(r'./popu_map.csv', encoding='utf-8', header=None))
e_load = 0.
e_population = 0.
population = min_max_scaler.fit_transform(population)
road = min_max_scaler.fit_transform(road)
population = population/population.sum()
road = road/road.sum()
pd.DataFrame(population).to_csv('population_map.csv', header=False, index=False)
for i in range(loc):
    for j in range(row):
        if road[i, j] != 0:
            e_load -= k * road[i, j] * log(road[i, j])
for i in range(loc):
    for j in range(row):
        if population[i, j] != 0:
            e_population -= k * population[i, j] * log(population[i, j])
h_load = 1 - e_load
h_population = 1 - e_population
w_load = h_load / (h_load + h_population)
w_population = h_population / (h_load + h_population)
weight_map = np.zeros((loc, row), dtype=float)
for i in range(loc):
    for j in range(row):
        weight_map[i, j] = w_load * road[i, j] + w_population * population[i, j]
weight_map = weight_map*1000
pd.DataFrame(weight_map).to_csv('weight_map.csv', header=False, index=False)
weight_map = min_max_scaler.fit_transform(weight_map)
a = 1