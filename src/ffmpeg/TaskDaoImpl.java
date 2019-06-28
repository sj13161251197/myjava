package ffmpeg;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 任务信息持久层实现
 * 
 * @author eguid
 * @since jdk1.7
 * @version 2016年10月29日
 */
public class TaskDaoImpl{
	// 存放任务信息
	private ConcurrentMap<String, TaskEntity> map = null;

	public TaskDaoImpl(int size) {
		map = new ConcurrentHashMap<>(size);
	}

	
	public TaskEntity get(String id) {
		return map.get(id);
	}
	
	
	public ConcurrentMap<String, TaskEntity> getMap() {
		return map;
	}


	public void setMap(ConcurrentMap<String, TaskEntity> map) {
		this.map = map;
	}


	public Collection<TaskEntity> getAll() {
		return map.values();
	}

	
	public int add(ffmpeg.TaskEntity tasker) {
		String id = tasker.getId();
		if (id != null && !map.containsKey(id)) {
			map.put(tasker.getId(), tasker);
			if(map.get(id)!=null)
			{
				return 1;
			}
		}
		return 0;
	}

	
	public int remove(String id) {
		if(map.remove(id) != null){
			return 1;
		};
		return 0;
	}

	
	public int removeAll() {
		int size = map.size();
		try {
			map.clear();
		} catch (Exception e) {
			return 0;
		}
		return size;
	}

	
	public boolean isHave(String id) {
		return map.containsKey(id);
	}

}
