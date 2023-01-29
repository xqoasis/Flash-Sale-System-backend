-- get available stock
-- 1. check key exists
if redis.call('exits', KEYS[1]) == 1
then
    --2.get available_stock
    local stock = tonumber(redis.call('get', KEYS[1]));

    --3. validate available_stock > 0
    if (stock > 0)
    then
        --4. update available_stock = available_stock - 1
        redis.call('set', KEYS[1], stock - 1);
        return stock - 1;
    end
    -- if available_stock <= 0, return -1
    return -1;

end
return -2;
-- set available_stock = available_stock - 1