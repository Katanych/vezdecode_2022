import requests

# входные данные могут быть представлены следующим образом:
# smile tourist k4t4ny4
# - то есть строка, состоящая из никнеймов, разделенными пробелами.
# все в духе спортпрог 

users = input().split()
users_dict = dict()

for user in users:
    count = 0
    a = requests.get(f"https://codeforces.com/api/user.status?handle={user}&from=1&count=100000000")
    tasks = dict()
    for i in a.json()["result"]:
        if i["problem"]["contestId"] not in tasks:
            tasks[i["problem"]["contestId"]] = []
        if i["problem"]["index"] not in tasks[i["problem"]["contestId"]]:
            tasks[i["problem"]["contestId"]].append(i["problem"]["index"])

    res = []
    for x in tasks.values():
        res.extend(x if isinstance(x, list) else [x])

    users_dict[user] = len(res)

for user in users_dict:
    print(user, ":", users_dict[user])
