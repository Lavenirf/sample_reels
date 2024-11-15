/**
 * 参数处理
 * @param params 参数
 * @returns 格式化后的查询字符串
 */
export function tansParams(params: Record<string, any>): string {
    let result = '';
    for (const propName of Object.keys(params)) {
      const value = params[propName];
      const part = encodeURIComponent(propName) + '=';
      if (value !== null && value !== '' && typeof value !== 'undefined') {
        if (typeof value === 'object') {
          for (const key of Object.keys(value)) {
            if (value[key] !== null && value[key] !== '' && typeof value[key] !== 'undefined') {
              const subParams = propName + '[' + key + ']';
              const subPart = encodeURIComponent(subParams) + '=';
              result += subPart + encodeURIComponent(value[key]) + '&';
            }
          }
        } else {
          result += part + encodeURIComponent(value) + '&';
        }
      }
    }
    return result.slice(0, -1); // 去掉最后一个 '&'
  }
  